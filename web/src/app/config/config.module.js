
/**
 * @ngInject
 */
export const ConfigModule = angular
  .module('app.config', [])
  .value('apiConfig', {
    baseUrl: 'http://localhost:7000/'
  })
  .name;