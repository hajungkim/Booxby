import itertools
from collections import Counter
#from parse import load_dataframes
import parse

import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import matplotlib.font_manager as fm

from matplotlib import font_manager,rc
import matplotlib

import folium

import numpy

def set_config():
    # 폰트, 그래프 색상 설정
    font_list = fm.findSystemFonts(fontpaths=None, fontext="ttf")
    if any(["notosanscjk" in font.lower() for font in font_list]):
        plt.rcParams["font.family"] = "Noto Sans CJK JP"
    else:
        if not any(["malgun" in font.lower() for font in font_list]):
            raise Exception(
                "Font missing, please install Noto Sans CJK or Malgun Gothic. If you're using ubuntu, try `sudo apt install fonts-noto-cjk`"
            )

        plt.rcParams["font.family"] = "Malgun Gothic"

    sns.set_palette(sns.color_palette("Spectral"))
    plt.rc("xtick", labelsize=6)


def show_store_categories_graph(dataframes, n=100):
    """
    Tutorial: 전체 음식점의 상위 `n`개 카테고리 분포를 그래프로 나타냅니다.
    """

    stores = dataframes["stores"]

    # 모든 카테고리를 1차원 리스트에 저장합니다
    print(stores,'1')

    categories = stores.category.apply(lambda c: c.split("|"))
    print(categories,'2')

    categories = itertools.chain.from_iterable(categories)
    print(categories,'3')

    # 카테고리가 없는 경우 / 상위 카테고리를 추출합니다
    categories = filter(lambda c: c != "", categories)
    print(categories,'4')

    categories_count = Counter(list(categories))
    print(categories,'5')

    best_categories = categories_count.most_common(n=n)
    print(best_categories,'6')

    df = pd.DataFrame(best_categories, columns=["category", "count"]).sort_values(
        by=["count"], ascending=False
    )

    # 그래프로 나타냅니다
    chart = sns.barplot(x="category", y="count", data=df)
    chart.set_xticklabels(chart.get_xticklabels(), rotation=45)
    plt.title("음식점 카테고리 분포")
    plt.show()


def show_store_review_distribution_graph(dataframes):
    """
    Req. 1-3-1 전체 음식점의 리뷰 개수 분포를 그래프로 나타냅니다.
    리뷰가 0 개인게 몇개
    1개인게 몇개
    2개인게 몇개 이거인듯?

    x축이 리뷰의 개수
    y축이 리뷰수인 가게 수
    """

    data = dataframes["stores"]
    df = data["review_cnt"].value_counts().reset_index()

    # 그래프 그리기
    chart = sns.barplot(x="index", y="review_cnt", data=df)
    chart.set_xticklabels(chart.get_xticklabels(), rotation=45)
    plt.title("Req 3-1. 음식점 리뷰 개수 분포")
    plt.show()

    #raise NotImplementedError


def show_store_average_ratings_graph(dataframes):
    """
    Req. 1-3-2 각 음식점의 평균 평점 분포를 그래프로 나타냅니다.

    x축은 음식점 이름
    y축은 평점
    """

    stores_reviews = pd.merge(
        dataframes["stores"], dataframes["reviews"], left_on="id", right_on="store"
    )

    scores_group = stores_reviews.groupby(["store", "store_name"])

    scores = scores_group.mean()

    scores = scores["score"].value_counts().reset_index().round(1)

    # 그래프 그리기
    chart = sns.barplot(x="index", y="score", data=scores)
    chart.set_xticklabels(chart.get_xticklabels(), rotation=45)
    plt.title("Req 3-2. 음식점 평점 분포")
    plt.show()

    #raise NotImplementedError


def show_user_review_distribution_graph(dataframes):
    """
    Req. 1-3-3 전체 유저의 리뷰 개수 분포를 그래프로 나타냅니다.
    """
    review = dataframes["reviews"]["user"].value_counts().reset_index()
    review = review["user"].value_counts().reset_index()

    #print(review)

    # 그래프 그리기
    chart = sns.barplot(x="index", y="user", data=review)
    chart.set_xticklabels(chart.get_xticklabels(), rotation=45)
    plt.title("Req 3-3. 유저의 리뷰 개수 분포")
    plt.show()


    #raise NotImplementedError


def make_age(x):
    return int(x/10) * 10

def show_user_age_gender_distribution_graph(dataframes):
    """
    Req. 1-3-4 전체 유저의 성별/나이대 분포를 그래프로 나타냅니다.
    """
    users = dataframes["users"]

    male = users[users["gender"]=="남"]
    male = male["age"].apply(make_age).value_counts().reset_index()

    female = users[users["gender"]=="여"]
    female = female["age"].apply(make_age).value_counts().reset_index()

    male.columns=["age","usercount"]
    female.columns = ["age", "usercount"]

    # 그래프 그리기
    chart = sns.barplot(x="age", y="usercount", data=male)
    chart.set_xticklabels(chart.get_xticklabels(), rotation=45)
    plt.title("Req 3-4. 남자 유저 나이대별 분포")
    plt.show()

    # 그래프 그리기
    chart = sns.barplot(x="age", y="usercount", data=female)
    chart.set_xticklabels(chart.get_xticklabels(), rotation=45)
    plt.title("Req 3-4. 여자 유저 나이대별 분포")
    plt.show()

    #raise NotImplementedError

def show_stores_distribution_graph(dataframes):
    """
    Req. 1-3-5 각 음식점의 위치 분포를 지도에 나타냅니다.
    """
    stores = dataframes["stores"].head(n=500).reset_index()
    print(stores)

    m = folium.Map(
        location=[36.5053542, 127.7043419],
        zoom_start=8,
        tiles='Cartodb Positron'
    )

    for i in range(len(stores)):
        folium.Circle(
            [stores["latitude"][i],
            stores["longitude"][i]] ,
           tooltip=stores["store_name"][i],
        ).add_to(m)

    m.save('result.html')


    #raise NotImplementedError

def user_store_matrix(dataframes):
    """
        Req. 1-4-1 유저-음식점 sparse 행렬 만들기
    """
    stores_reviews = pd.merge(
        dataframes["stores"], dataframes["reviews"], left_on="id", right_on="store"
    )

    user_list = list(set(stores_reviews['user'].values.tolist()))
    user_list.sort()

    store_list = list(set(stores_reviews["store_name"].values.tolist()))

    df = pd.DataFrame(data=numpy.nan, index=user_list, columns=store_list)

    user_group = stores_reviews.sort_values(by='user').groupby(['user','store_name']).mean().loc[:,'score']

    for index, score in user_group.items():
        user, store_name = index
        df.loc[user,store_name]=score

    print(df)

def user_category_matrix(dataframes):
    """
        Req. 1-4-2 유저-카테고리 sparse 행렬 만들기
    """
    stores_reviews = pd.merge(
        dataframes["stores"], dataframes["reviews"], left_on="id", right_on="store"
    )

    user_list = list(set(stores_reviews['user'].values.tolist()))
    user_list.sort()

    store_list = list(set(stores_reviews["category"].values.tolist()))

    df = pd.DataFrame(data=numpy.nan, index=user_list, columns=store_list)

    user_group = stores_reviews.sort_values(by='user').groupby(['user','category']).mean().loc[:,'score']

    for index, score in user_group.items():
        user, category = index
        df.loc[user,category]=score

    print(df)

def main():

    #set_config()
    font_path = "C:/Windows/Fonts/SeoulNamsanB.TTF"
    font_name = font_manager.FontProperties(fname=font_path).get_name()
    matplotlib.rc('font', family=font_name)

    data = parse.load_dataframes()

    # show_store_categories_graph(data)
    show_store_review_distribution_graph(data)
    show_store_average_ratings_graph(data)
    show_user_review_distribution_graph(data)
    show_user_age_gender_distribution_graph(data)
    show_stores_distribution_graph(data)

    """
    Req 4번
    """
    user_store_matrix(data)
    user_category_matrix(data)

if __name__ == "__main__":
    main()
