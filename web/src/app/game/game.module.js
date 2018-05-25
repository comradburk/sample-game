import angular from 'angular';
import { GameListModule } from './game-list/game-list.module';
import { GameBoardModule } from './game-board/game-board.module';
import { GameService } from './services/game.service';

export const GameModule = angular
  .module('app.game', [
    GameListModule,
    GameBoardModule,
  ])
  .service('GameService', GameService)
  .name;