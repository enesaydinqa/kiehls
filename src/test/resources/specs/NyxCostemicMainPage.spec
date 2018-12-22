==================================================================================================
@objects
    ProHeaderSpace                 css         .pro-header-space
    MainPageSlider                 xpath       //div[contains(@id, 'pro-spotlight-')]
    BestNewHeader                  xpath       (//div[@class='pro-content-list-header'])[1]
    BestNewProductContainer        xpath       (//div[@class='slick-list draggable'])[1]/div/div[contains(@class,'slick-active')]
    BestSellersHeader              xpath       (//div[@class='pro-content-list-header'])[2]
    BestSellersProductContainer    xpath       (//div[@class='slick-list draggable'])[2]/div/div[contains(@class,'slick-active')]
    NoToMissHeader                 xpath       (//div[contains(@class, 'v-widget-container')])[1]
    NoToMissImage                  xpath       (//div[contains(@class, 'proadmin-cms-place-row')])[6]
    MoreHeader                     xpath       (//div[contains(@class, 'v-widget-container')])[2]
    MoreImage                      xpath       (//div[contains(@class, 'proadmin-cms-place-row')])[8]
    Footer                         xpath       (//div[contains(@class, 'proadmin-cms-place-row')])[9]

==================================================================================================

= Native Ad Classified Layout Page =

  @on desktop

    ProHeaderSpace :
        image file images/ReportsPage/ReportsHeader.png, error 3 %

    BestNewHeader :
        image file images/ReportsPage/ReportsHeader.png, error 3 %

    BestSellersHeader :
        image file images/ReportsPage/ReportsHeader.png, error 3 %

    NoToMissHeader :
        image file images/ReportsPage/ReportsHeader.png, error 3 %

    NoToMissImage :
        image file images/ReportsPage/ReportsHeader.png, error 3 %

    MoreHeader :
        image file images/ReportsPage/ReportsHeader.png, error 3 %

    MoreImage :
        image file images/ReportsPage/ReportsHeader.png, error 3 %

    Footer :
        image file images/ReportsPage/ReportsHeader.png, error 3 %

    sahibinden-logo:
        height 34px
        width 160px
    classifiedBreadCrumbBackground:
        height 27px
        width 100 % of screen/width
    classifiedBreadCrumb:
        height 100 % of classifiedBreadCrumbBackground/height
        width 984 to 1150px
        inside classifiedBreadCrumbBackground 0px top
    searchResultsPage:
        width 984 to 1150px
        below classifiedBreadCrumb 0px
    searchResultsContainer:
        width 984 to 1150px
        inside searchResultsPage 0px left
    search-left:
        width 200px
        inside searchResultsContainer 0px left, 10px top
        css font-family contains "Lucida Grande"
        #css font-family ends "sans-serif"
        #css font-family contains "Arial"
        #css font-family contains "Helvatica"
        #css font-family matches ".*Arial.*"

    #category items
    @forEach [searchCats-*] as itemName, next as nextItem
        ${itemName}:
            height 23px
            above ${nextItem} 0px
            css font-size is "12px"
            css font-family contains "Lucida Grande"
            css font-family ends "sans-serif"
            css font-family contains "Arial"
            css font-family matches ".*Arial.*"

    searchResultLeft-address:
        width 200px
        inside search-left 0px left, 0px right
    searchResultLeft-price:
         width 200px
         inside search-left 0px left, 0px right
    searchResultsRight:
         width 774 to 934px
         inside searchResultsContainer 0px right, 10px top
    relativeContainer:
         width 774 to 934px
         inside searchResultsRight 0px left, 0px right
    infoSearchResults:
         width 774 to 934px
         inside relativeContainer 0px left, 0px right
    searchResultSorted:
         width 774 to 934px
         height 40px
         below infoSearchResults 8px
         inside relativeContainer 0px left, 0px right
    sortedTypes:
         height 26px
         inside searchResultSorted 9px top, 0px right
    view-type-size-menu-item:
         width 97px
         height 25px
         inside searchResultSorted 9px top
    sort-order-menu:
         width 211px
         height ~ 25px
         inside searchResultSorted 9px top
         right-of view-type-size-menu-item 10px
    searchResultsTable:
         width 774 to 934px
         inside searchResultsRight 0px left, 0px right
    searchResultsTableHeader:
         width 772 to 932px
         height 30px
         inside searchResultsTable 1px left, 1px right

    #search result rows
    @for [1 - 9] as index
        searchResultRow-${index}:
            above searchResultRow-${index + 1} 0 to 83px
            width 772 to 932px
            inside searchResultsTable 1px left, 1px right
            css font-size is "12px"
            css font-family contains "Lucida Grande"
            css font-family ends "sans-serif"
            css font-family contains "Arial"
            css font-family matches ".*Arial.*"

    footerContainer:
        width 100 % of screen/width
        height 413 to 428 px
    footer:
        width 984 to 1150px
        height 413 to 426 px

    header-banner :
        width 728 to 970 px
        height 90 px
        inside screen 8 px top
    ad-image :
        width 100 px
        height 75 px
        inside screen 571 px top
        near ad-call-to-action 607 to 763 px left
    ntvd-link-container :
        width 772 to 932 px
        height 41 px
        inside screen 585 px top
    ad-call-to-action :
        width 56 to 60 px
        height 30 px
        inside screen 598 px top
        near ad-image 607 to 763 px right