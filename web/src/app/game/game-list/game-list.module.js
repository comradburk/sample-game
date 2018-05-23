import angular from 'angular';
import { GameListComponent } from './game-list.component';
import { GameListService } from './game-list.service';

export default angular
  .module('gameList', [])
  .component('gameList', GameListComponent)
  .service('GameListService', GameListService)
  .name;