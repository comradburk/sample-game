import angular from 'angular';
import dependenciesModule from './dependencies/dependencies.module';
import GameListModule from './game/game-list/game-list.module';

import '../style/app.css';
import '../../node_modules/font-awesome/css/font-awesome.css';
import '../../node_modules/angular-material/angular-material.css';


let app = () => {
  return {
    template: require('./app.html'),
    controller: 'AppCtrl',
    controllerAs: 'app'
  }
};

class AppCtrl {
  constructor() {
    this.url = 'https://github.com/preboot/angular-webpack';
  }
}

const MODULE_NAME = 'app';

angular.module(MODULE_NAME, [dependenciesModule, GameListModule])
  .directive('app', app)
  .controller('AppCtrl', AppCtrl);

//angular.bootstrap(document.documentElement, ['app']);