package com.GanApp.viewsganapp.navigation

sealed class AppScreens(val route: String) {
    object loginUser : AppScreens("loginUser_screens")
    object viewReister : AppScreens("viewRegister")
    object conecctionFacebook : AppScreens ("facebook")
    object conecctionGmail : AppScreens("gmail")
    object forgotPassword : AppScreens ("forgotPassword")
    object resetPassword : AppScreens("resetPassword")
    object productRegister : AppScreens("productRegister")
    object homePage: AppScreens ("homePage")
    object profile : AppScreens ("Profile_screens")
    object reviews : AppScreens ("reviews")
    object catalogo : AppScreens("catalogo")
    object detalleProd : AppScreens("detalleProd/{productId}")
    object editProfile : AppScreens ("edit_profile")
    object favorite: AppScreens ("favorito")
    object CreateChatView : AppScreens("CreateChatView")
    object ChatView : AppScreens("ChatView")
    object ChatMessages : AppScreens("chat_message/{chatId}")
    object menuDetalleProd : AppScreens("menuDetalleProd/{productId}")
}
