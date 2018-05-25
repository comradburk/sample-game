export class GameService {
  constructor($http, apiConfig) {
    'ngInject';
    this.$http = $http;
    this.apiConfig = apiConfig;
  }

  newGame() {
    return this.$http
      .post(this.apiConfig.baseUrl + 'game')
      .then(response => response.data);
  }

  getGames() {
    return this.$http
      .get(this.apiConfig.baseUrl + 'game')
      .then(response => response.data.map(game => translateGameResponse(game)));
  }

  getGame(gameId) {
    return this.$http
      .get(this.apiConfig.baseUrl + 'game/' + gameId)
      .then(response => translateGameResponse(response.data));
  }

  performMove(gameId, gamePlayer, playerPitIndex) {
    let url = this.apiConfig.baseUrl + 'game/' + gameId + '/move';
    return this.$http.post(url, {
        gamePlayer: gamePlayer,
        playerPitIndex: playerPitIndex,
    }).then(response => translateGameResponse(response.data));
  }

  translateGameResponse(game){
    const playerOneField = 'playerOne';
    const playerTwoField = 'playerTwo';
    let playerOnePits = game.pits[playerOneField];
    let playerTwoPits = game.pits[playerTwoField];

    var gameBoard = {
      id: game.id,
      gameState: game.gameState,
      currentPlayer: game.currentPlayer,
      playerScore: {
        [playerOneField]: playerOnePits[playerOnePits.Length - 1],
        [playerTwoField]: playerTwoPits[playerTwoPits.Length - 1],
       },
      playerPits: {
        [playerOneField]: playerOnePits.slice(0, playerOnePits.Length - 2),
        [playerTwoField]: playerTwoPits.slice(0, playerOnePits.Length - 2),
      },
    };

    return gameBoard;
  }
}
