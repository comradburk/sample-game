import angular from 'angular';
import { GameBoardComponent } from './game-board.component';
import { GameBoardService } from './game-board.service';

export const GameBoardModule = angular
  .module('app.gameBoard', [])
  .component('gameBoard', GameBoardComponent)
  .service('GameBoardService', GameBoardService)
  .name;