(function($) {

    "use strict";

    // Selectors caching
    var doc             =   $(document),
        win             =   $(window),
        section         =   $('section'),
        mainHeader      =   $('header'),
        amount          =   $('#amount'),
        amount1         =   $('#amount1'),
        amount2         =   $('#amount2'),
        rangeSlider     =   $('#slider-range'),
        accordion2      =   $('#accordion2'),
        accordion3      =   $('#accordion3'),
        countWrap       =   $('.count-wrap'),
        productSlide    =   $('.product-slide'),
        slideParallax   =   $('.slide-parallax'),
        eventSlide      =   $('.event-slider'),
        detailSlide     =   $('.detail-slide'),
        thumbSlide      =   $('.thumb-slider'),
        dtPicker        =   $('.form_datetime'),
        testiSlide      =   $('.testimonial-slider'),
        countBlock      =   $('.counter-block'),
        testiBlock      =   $('.testimonial-block'),
        scroller        =   $('.scrollup'),
        timer           =   $('.timer'),
        swiperLoader    =   $('.swiper-loader');


    // While window scrolls
    win.on('scroll', function() {
        var $this = $(this);
        var scrollTop = $this.scrollTop();

        if (scrollTop > 60) { //Adjust 150
            mainHeader.addClass('shrinked');
        } else {
            mainHeader.removeClass('shrinked');
        }

    	// For Parallax offsetting
        section.each(function() {
            var off = $(this).data('orig-offset');

            if (scrollTop >= off) {
                var translate = (scrollTop - off) / win.height() * 100;
                slideParallax.css({ transform: 'translateY(-' + translate + 'px)' });
            }
        });

        // Hide/show the back to top button
        if (scrollTop > 400) {
            scroller.fadeIn();
        } else {
            scroller.fadeOut();
        }
    });

    // wow animation effect
    var wow = new WOW({
        boxClass: 'wow',
        animateClass: 'animated',
        offset: 100,
    });
    wow.init();

    // banner slider
    var swiper = new Swiper('.swiper-container', {
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        slidesPerView: 1,
        paginationClickable: true,
        spaceBetween: 500,
        loop: true,
        effect: 'fade', 
        lazyLoading: true,
        preloadImages: false,
        autoplay: 3500,
        onInit: function(e){
            swiperLoader.hide();
        }
    });

    //baner parallax
    section.each(function() {
        var off = $(this).offset().top
        $(this).data('orig-offset', off);
    });

    //screen-height
    function setHeight() {
        var windowHeight = win.innerHeight();
        $('.home-slider, .slide-parallax-move, .confrence-content').css('height', windowHeight + "px");
    };
    setHeight();

    // window resize event
    win.on('resize', function() {
        setHeight();
    });

    // event slider
    eventSlide.bxSlider({
        auto: true,
        pager: true,
        controls: false
    });

    // event detail slider
    detailSlide.bxSlider({
        auto: true,
        pager: false,
        controls: true,
        nextText: '<i class="fa fa-chevron-right"></i>',
        prevText: '<i class="fa fa-chevron-left"></i>'
    });

    // testimonial-slider
    testiSlide.bxSlider({
        pagerCustom: '.testimonial-thumb',
        auto: true,
        pager: true,
        controls: false
    });

    //thumbnail slider
    thumbSlide.bxSlider({
        pagerCustom: '#thumb-pager',
        auto: true,
        controls: false
    });

    //related product slide
    if (win.width() < 767) {
        productSlide.bxSlider({
            minSlides: 1,
            maxSlides: 1,
            slideMargin: 0,
            controls: true,
            nextText: '<i class="fa fa-angle-right"></i>',
            prevText: '<i class="fa fa-angle-left "></i>',
            pager: false
        });
    } else {
        productSlide.bxSlider({
            auto: true,
            controls: false,
            pager: false,
            minSlides: 3,
            maxSlides: 3,
            slideWidth: 270,
            slideMargin: 30,
            moveSlides: 1
        });
    }

    // Scroll to top button event
    scroller.on("click", function() {
        $("html, body").animate({
            scrollTop: 0
        }, 1500);
        return false;
    });

    // date time picker
    dtPicker.datetimepicker({ format: 'yyyy-mm-dd' });

    //parallax blocks

    if (win.width() > 1024) {
        testiBlock.parallax("50%", 0.05);
        countBlock.parallax("50%", 0.05);  
    }  

    // start all the timers
    timer.each(count);

    function count(options) {
        var $this = $(this);
        options = $.extend({}, options || {}, $this.data('countToOptions') || {});
        $this.countTo(options);
    }

    //count down
    if (countWrap.length) {
        var endDate = new Date(countWrap.data("end-date"));
        countWrap.countdown({
            date: endDate,
            render: function(data) {
                $(this.el).html(
                    '<div class="box"><span class="no">' + this.leadingZeros(data.days, 2) + '</span><hr><span></span>DAYS</div>' +
                    '<div class="box"><span class="no">' + this.leadingZeros(data.hours, 2) + '</span><hr><span></span>HOURS</div>' +
                    '<div class="box"><span class="no">' + this.leadingZeros(data.min, 2) + '</span><hr><span></span>MIN</div>' +
                    '<div class="box"><span class="no">' + this.leadingZeros(data.sec, 2) + '</span><hr><span></span>SEC</div>'
                );
            }
        });
    }

    //price range
    rangeSlider.slider({
        range: true,
        min: 0,
        max: 500,
        values: [30, 400],
        slide: function(event, ui) {
            amount.html("$" + ui.values[0] + " - $" + ui.values[1]);
            amount1.val(ui.values[0]);
            amount2.val(ui.values[1]);
        }
    });

    amount.html("$" + rangeSlider.slider("values", 0) +
            " - $" + rangeSlider.slider("values", 1));

    // schedule accordion
    accordion2.accordion({
        heightStyle: "content"
    });

    //faq
    accordion3.accordion({
        header: '> div.acc-wrap >h4',
        heightStyle: "content"
    });

})(jQuery);