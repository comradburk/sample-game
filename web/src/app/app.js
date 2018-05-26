import angular from 'angular';
import { DependenciesModule } from './dependencies/dependencies.module';
import { ConfigModule } from './config/config.module';
import { GameModule } from './game/game.module';

import '../style/app.css';


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
  
export default MODULE_NAME;