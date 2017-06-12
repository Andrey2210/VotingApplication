$(document).ready(function () {
        var poll_bars_elements = $(".poll_bar");
        var poll_val_elements = $(".poll_val");
        poll_bars_elements.addClass("btn btn-default");
        poll_bars_elements.css({"text-align": "left", "margin": "5px"});
console.log(poll_bars_elements);
console.log(poll_val_elements);
        /* Set Bar color as per rules.
         value >= 50 bar color green.
         value < 50 bar color orange.
         value <= 10 bar color red. */
        poll_bars_elements.each(
            function (i) {
                var bar_width = (parseFloat(poll_val_elements.eq(i).text()) / 2);
                console.log(bar_width);
                poll_bars_elements.eq(i).width(bar_width + "%");

                //Define rules.
                if (bar_width >= 50) {
                    poll_bars_elements.eq(i).addClass("btn btn-sm btn-success")
                }
                if (bar_width < 50) {
                    poll_bars_elements.eq(i).addClass("btn btn-sm btn-warning")
                }
                if (bar_width <= 10) {
                    poll_bars_elements.eq(i).addClass("btn btn-sm btn-danger")
                }
            });
});
