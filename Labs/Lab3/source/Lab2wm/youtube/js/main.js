var app = angular.module('instantYoutubeSearch', ['ui.router']);

app.config(function($stateProvider,$urlRouterProvider){
    $urlRouterProvider.otherwise("home");
    $stateProvider.state("home",{
        url:"/home",
        templateUrl:"Html_pages/home.html"
    });
});