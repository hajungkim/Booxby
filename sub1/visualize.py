import itertools
from collections import Counter
from parse import load_dataframes
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import matplotlib.font_manager as fm
import folium as folium


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
    categories = stores.category.apply(lambda c: c.split("|"))
    categories = itertools.chain.from_iterable(categories)

    # 카테고리가 없는 경우 / 상위 카테고리를 추출합니다
    categories = filter(lambda c: c != "", categories)
    categories_count = Counter(list(categories))
    best_categories = categories_count.most_common(n=n)
    df = pd.DataFrame(best_categories, columns=["category", "count"]).sort_values(
        by=["count"], ascending=False
    )

    # 그래프로 나타냅니다
    chart = sns.barplot(x="category", y="count", data=df)
    chart.set_xticklabels(chart.get_xticklabels(), rotation=45)
    plt.title("음식점 카테고리 분포")
    plt.show()

def show_store_review_distribution_graph(dataframes, n=100):
    """
    Req. 1-3-1 전체 음식점의 리뷰 개수 분포를 그래프로 나타냅니다. 
    """
    store = dataframes["stores"]
    cnt = store["review_cnt"].value_counts().reset_index()

    # 그래프로 나타냅니다
    chart = sns.barplot(x="index", y="review_cnt", data=cnt)
    chart.set_xticklabels(chart.get_xticklabels(), rotation=45)
    plt.title("음식점 리뷰 개수 분포")
    plt.show()
    # raise NotImplementedError


def show_store_average_ratings_graph(dataframes, n=100):
    """
    Req. 1-3-2 각 음식점의 평균 평점을 그래프로 나타냅니다.
    """
    stores_reviews = pd.merge(
        dataframes["stores"], dataframes["reviews"], left_on="id", right_on="store"
    )
    reviews_group = stores_reviews.groupby(["store", "store_name"])
    review = reviews_group.mean()
    
    score = review["score"].value_counts().reset_index().round(1)
    chart = sns.barplot(x="index", y="score", data=score)
    chart.set_xticklabels(chart.get_xticklabels(), rotation=45)
    plt.title("음식점 평균 분포")
    plt.show()
    

    # raise NotImplementedError


def show_user_review_distribution_graph(dataframes):
    """
    Req. 1-3-3 전체 유저의 리뷰 개수 분포를 그래프로 나타냅니다.
    """
    reviews = dataframes["reviews"]
    ct = reviews["user"].value_counts().head(10).reset_index()
    ct.columns = ['user_id','count']

    chart = sns.barplot(x="user_id", y="count", data=ct)
    chart.set_xticklabels(chart.get_xticklabels(), rotation=45)
    plt.title("유저 리뷰 개수 분포")
    plt.show()

    # raise NotImplementedError


def show_user_age_gender_distribution_graph(dataframes):
    """
    Req. 1-3-4 전체 유저의 성별/나이대 분포를 그래프로 나타냅니다.
    """
    users = dataframes["users"]
    gender = users["gender"].value_counts().reset_index()
    age = users["age"].value_counts().reset_index()

    chart = sns.barplot(x="index", y="gender", data=gender)
    chart.set_xticklabels(chart.get_xticklabels(), rotation=45)

    plt.title("유저의 성별 분포")
    plt.show()
    chart = sns.barplot(x="index", y="age", data=age)
    chart.set_xticklabels(chart.get_xticklabels(), rotation=45)
    plt.title("유저의 나이 분포")
    plt.show()
    # raise NotImplementedError


def show_stores_distribution_graph(dataframes):
    """
    Req. 1-3-5 각 음식점의 위치 분포를 지도에 나타냅니다.
    """
    # stores = dataframes["stores"].head(n=100).reset_index()
    # map = folium.Map(location=[37.541, 126.986], zoom_start=10)
    # marker_cluster = MarkerCluster().add_to(map)

    # for i in range(len(stores)):
    #     folium.Marker([stores["latitude"][i], stores["longitude"][i]]).add_to(marker_cluster)
   
    # marker = folium.Marker([37.541, 126.986])
    # marker.add_to(m)

    m.save('result.html')
    
    # raise NotImplementedError

def main():
    set_config()
    data = load_dataframes()
    # show_store_categories_graph(data)
    # show_store_review_distribution_graph(data)
    # show_store_average_ratings_graph(data)
    # show_user_review_distribution_graph(data)
    show_user_age_gender_distribution_graph(data)
    show_stores_distribution_graph(data)

if __name__ == "__main__":
    main()
