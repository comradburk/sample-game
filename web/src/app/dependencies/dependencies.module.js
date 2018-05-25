// Imports
import angular from 'angular';
import angularAnimate from 'angular-animate';
import angularAria from 'angular-aria';
import angularSanitize from 'angular-sanitize';
import angularMessages from 'angular-messages';
import angularMaterial from 'angular-material';

import config from './dependencies.config';

/**
 * @ngInject
 */
export const DependenciesModule = angular
  .module('app.dependencies', [
    angularAnimate, 
    angularAria, 
    angularSanitize,
    angularMessages,
    angularMaterial, 
  ])
  .config(config)
  .run()
  .name;