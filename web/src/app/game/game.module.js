import angular from 'angular';
import { GameBoardModule } from './game-board/game-board.module';
import { GameService } from './services/game.service';

export const GameModule = angular
  .module('app.game', [
    GameBoardModule,
  ])
  .service('GameService', GameService)
  .value('GamePlayers', {
    playerOne: 'playerOne',
    playerTwo: 'playerTwo',
  })
  .name;