import angular from 'angular';
import { GameListComponent } from './game-list.component';

export const GameListModule = angular
  .module('app.gameList', [])
  .component('gameList', GameListComponent)
  .name;