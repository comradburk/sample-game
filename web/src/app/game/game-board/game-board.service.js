export class GameBoardService {
  constructor($http) {
    'ngInject';
    this.$http = $http;
  }

  performMove(gameId, gamePlayer, playerPitIndex) {
    let url = 'http://localhost:7000/game/' + gameId + '/move';
    return this.$http.post(url, {
        gamePlayer: gamePlayer,
        playerPitIndex: playerPitIndex,
    }).then(response => response.data);
  }
}