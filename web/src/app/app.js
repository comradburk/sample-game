import angular from 'angular';
import { DependenciesModule } from './dependencies/dependencies.module';
import { ConfigModule } from './config/config.module';
import { GameModule } from './game/game.module';

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
  }
}

const MODULE_NAME = 'app';

angular.module(MODULE_NAME, [
    DependenciesModule,
    ConfigModule,
    GameModule
  ])
  .directive('app', app)
  .controller('AppCtrl', AppCtrl);

//angular.bootstrap(document.documentElement, ['app']);