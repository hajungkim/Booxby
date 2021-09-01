import itertools
from collections import Counter
from parse import load_dataframes
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import matplotlib.font_manager as fm
import numpy as np
import folium

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
    """
    data = dataframes["stores"]
    df = data["review_cnt"].value_counts().reset_index()
    df.columns = ['review_cnt','count']
    # 그래프 그리기
    chart = sns.barplot(x="review_cnt", y="count", data=df)
    chart.set_xticklabels(chart.get_xticklabels(), rotation=45)
    plt.title("음식점 리뷰 개수 분포")
    plt.show()

    raise NotImplementedError


def show_store_average_ratings_graph(dataframes):
    """
    Req. 1-3-2 각 음식점의 평균 평점을 그래프로 나타냅니다.
    """
    stores_reviews = pd.merge(dataframes["stores"], dataframes["reviews"], left_on="id", right_on="store")
    scores_group = stores_reviews.groupby(["store", "store_name"])
    scores = scores_group.mean()
    # 소수점 2번째에서 반올림 편의를 위해
    df = scores["score"].value_counts().reset_index().round(1)
    df.columns = ['avgscore','count']

    # 그래프 그리기
    chart = sns.barplot(x="avgscore", y="count", data=df)
    chart.set_xticklabels(chart.get_xticklabels(), rotation=45)
    plt.title("음식점 평균 평점 분포")
    plt.show()
    raise NotImplementedError


def show_user_review_distribution_graph(dataframes):
    """
    Req. 1-3-3 전체 유저의 리뷰 개수 분포를 그래프로 나타냅니다.
    """
    df = dataframes["reviews"]["user"].value_counts().reset_index()
    df = df["user"].value_counts().head(20).reset_index()
    df.columns = ['user_review_cnt','count']

    chart = sns.barplot(x="user_review_cnt", y="count", data=df)
    chart.set_xticklabels(chart.get_xticklabels(), rotation=45)
    plt.title("유저 리뷰 개수 분포")
    plt.show()
    raise NotImplementedError


def show_user_age_gender_distribution_graph(dataframes):
    """
    Req. 1-3-4 전체 유저의 성별/나이대 분포를 그래프로 나타냅니다.
    """
    def age_categorize(age):
        if age < 10: return 10  #10살 미만 10대로 칭함
        age = (age // 10) * 10 
        return age

    df = dataframes["users"]
    df = df[df.age != 2022]
    male_df = df[df["gender"] == "남"]
    female_df = df[df["gender"] == "여"]
    
    male_df = male_df.age.apply(age_categorize).value_counts().reset_index()
    female_df = female_df.age.apply(age_categorize).value_counts().reset_index()

    male_df.columns = ['age_category','count']
    female_df.columns = ['age_category','count']

    chart = sns.barplot(x="age_category", y="count", data=male_df)
    chart.set_xticklabels(chart.get_xticklabels(), rotation=45)
    plt.title("남자 나이대 분포")
    plt.show()

    chart2 = sns.barplot(x="age_category", y="count", data=female_df)
    chart2.set_xticklabels(chart2.get_xticklabels(), rotation=45)
    plt.title("여자 나이대 분포")
    plt.show()
    raise NotImplementedError


def show_stores_distribution_graph(dataframes):
    """
    Req. 1-3-5 각 음식점의 위치 분포를 지도에 나타냅니다.
    """
    df = dataframes["stores"]["address"].reset_index()
    # 주소에 맨 앞만 region 컬럼에 저장 ex) 서울특별시,광주광역시,부산광역시 ....
    df["region"] = df.address.str.split(' ').str[0]
    df = df["region"].value_counts().reset_index()
    df.columns = ['region','count']

    with open('../data/TL_SCCO_CTPRVN.json', 'r', encoding='utf-8') as f:
        geo_data = f.read()

    center = [37.541, 126.986]

    m = folium.Map(location=center, zoom_start=10)

    folium.Choropleth(
        geo_data=geo_data,
        data=df,
        columns=('region', 'count'),
        key_on='feature.properties.CTP_KOR_NM',
        fill_color='BuPu',
        legend_name='store location',
    ).add_to(m)
    
    m.save('결과.html')

    raise NotImplementedError

def show_user_store_matrix(dataframes):
    stores_reviews = pd.merge(
    dataframes["stores"], dataframes["reviews"], left_on="id", right_on="store"
    )
    df = stores_reviews[stores_reviews["user"] == 68632]
    print(df,'@@')
    df = stores_reviews[["user","store_name","score"]]
    df = df.groupby(df["user"])
    print(df.head(100),'!!')

def main():
    set_config()
    data = load_dataframes()
    # show_store_categories_graph(data)
    # show_store_review_distribution_graph(data)
    # show_store_average_ratings_graph(data)
    # show_user_review_distribution_graph(data)
    # show_user_age_gender_distribution_graph(data)
    # show_stores_distribution_graph(data)
    show_user_store_matrix(data)
if __name__ == "__main__":
    main()
