/**
 * Add all 3rd party library config stuff to this file.
 *
 * @ngInject
 *
 * @param {$mdThemingProvider}  $mdThemingProvider
 */
export default ($mdThemingProvider, $httpProvider) => {
  // Configure angular-material theme
  $mdThemingProvider
    .theme('default')
    .primaryPalette('indigo')
    .accentPalette('light-blue')
  ;

  $httpProvider.interceptors.push(function($q, $injector) {
    return {
      'responseError': function(rejection) {
        let $mdToast = $injector.get('$mdToast');
        let toastText = (!!rejection.data) ? rejection.data : "Unknown error.";
        // toast server error
        let errorToast = $mdToast.simple()
          .textContent(toastText)
          .position('bottom right')
          .highlightClass('md-warn');
        $mdToast.show(errorToast);

        return $q.reject(rejection);
      }
    };
  });
}