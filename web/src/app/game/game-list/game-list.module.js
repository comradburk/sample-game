import angular from 'angular';
import { GameListComponent } from './game-list.component';
import { GameListService } from './game-list.service';

export const GameListModule = angular
  .module('app.gameList', [])
  .component('gameList', GameListComponent)
  .service('GameListService', GameListService)
  .name;