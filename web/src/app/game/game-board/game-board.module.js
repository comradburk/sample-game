import angular from 'angular';
import { GameBoardComponent } from './game-board.component';

import './game-board.style.css';

export const GameBoardModule = angular
  .module('app.gameBoard', [])
  .component('gameBoard', GameBoardComponent)
  .name;