// Imports
import angular from 'angular';
import angularAnimate from 'angular-animate';
import angularAria from 'angular-aria';
import angularMaterial from 'angular-material';
import angularSanitize from 'angular-sanitize';

import config from './dependencies.config';

/**
 * @ngInject
 */
export default angular
  .module('app.dependencies', [
    angularAnimate, angularAria, angularMaterial, angularSanitize,
  ])
  .config(config)
  .run()
  .name;