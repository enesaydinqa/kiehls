==================================================================================================
@objects
    ProHeaderSpace                 css         .pro-header-space
    MainPageSlider                 xpath       //div[contains(@id, 'pro-spotlight-')]
    BestNewHeader                  xpath       (//div[@class='pro-content-list-header'])[1]
    BestNewProductContainer-*      xpath       (//div[@class='slick-list draggable'])[1]/div/div[contains(@class,'slick-active')]
    BestSellersHeader              xpath       (//div[@class='pro-content-list-header'])[2]
    BestSellersProductContainer-*  xpath       (//div[@class='slick-list draggable'])[2]/div/div[contains(@class,'slick-active')]
    NoToMissHeader                 xpath       (//div[contains(@class, 'v-widget-container')])[1]
    NoToMissImage                  xpath       (//div[contains(@class, 'proadmin-cms-place-row')])[6]
    MoreHeader                     xpath       (//div[contains(@class, 'v-widget-container')])[2]
    MoreImage                      xpath       (//div[contains(@class, 'proadmin-cms-place-row')])[8]
    Footer                         xpath       (//div[contains(@class, 'proadmin-cms-place-row')])[9]

==================================================================================================

= NyxCostemic Main Page Layout Design =

  @on desktop

    ProHeaderSpace :
        image file images/web/ProHeaderSpace.png, error 3 %

    BestNewHeader :
        image file images/web/BestNewHeader.png, error 3 %

    BestSellersHeader :
        image file images/web/BestSellersHeader.png, error 3 %

    NoToMissHeader :
        image file images/web/NoToMissHeader.png, error 3 %

    NoToMissImage :
        image file images/web/NoToMissImage.png, error 3 %

    MoreHeader :
        image file images/web/MoreHeader.png, error 3 %

    MoreImage :
        image file images/web/MoreImage.png, error 3 %

    Footer :
        image file images/web/Footer.png, error 3 %

    MainPageSlider:
        height 34px
        width 160px
        inside screen 0px left, 10px top

    @for [1 - 4] as index
        BestNewProductContainer-${index}:
            width 772 px
            height 34 px
            inside screen 0px top

    @for [1 - 4] as index
        BestSellersProductContainer-${index}:
            width 772 px
            height 34 px
            inside screen 0px top