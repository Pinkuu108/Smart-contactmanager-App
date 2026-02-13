console.log("This is Script File");

const toggleSidebar = () => {

    if ($(".sidebar").is(":visible")) {
        // sidebar visible → hide
        $(".sidebar").css("display", "none");
        $(".content").css("margin-left", "0%");
    } else {
        // sidebar hidden → show
        $(".sidebar").css("display", "block");
        $(".content").css("margin-left", "20%");
    }
};
