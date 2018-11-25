app.controller('youtubeController', function($scope,$http,$filter) {
    $scope.youtubeData = [];
    $scope.nextPage = "";
    $scope.youtubeSearchText = "";
    $scope.getYoutubeData = function(searchText){
        $http.get('https://www.googleapis.com/youtube/v3/search', {
            params: {
                key: "AIzaSyB0TR1Nz9lZHFt5p-yu9K2G_ZG5Mr_3DNo",
                type: 'video',
                maxResults: '12',
                pageToken: $scope.nextPage ? $scope.nextPage : '',
                part: 'id,snippet',
                fields: 'items/id,items/snippet/title,items/snippet/description,items/snippet/thumbnails/default,items/snippet/channelTitle,nextPageToken,prevPageToken',
                q: searchText
            }
        }).success( function (data) {
            if (data.items.length === 0) {
                $scope.youtubeData = 'No results were found!';
            }
            $scope.youtubeSearchText = searchText;
            $scope.youtubeData = data.items;
            $scope.nextPageToken = data.nextPageToken;
            $scope.prevPageToken = data.prevPageToken;
        });
    };

    $scope.checkDataLength = function(data){
        return (data.length >=1);
    };

    $scope.callNextPageFn = function(nextPage){
        $scope.nextPage = nextPage;
        $scope.getYoutubeData($scope.youtubeSearchText);
    };
});