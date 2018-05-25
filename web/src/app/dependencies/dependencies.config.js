/**
 * Add all 3rd party library config stuff to this file.
 *
 * @ngInject
 *
 * @param {$mdThemingProvider}  $mdThemingProvider
 */
export default ($mdThemingProvider) => {
  // Configure angular-material theme
  $mdThemingProvider
    .theme('default')
    .primaryPalette('indigo')
    .accentPalette('light-blue')
  ;
};