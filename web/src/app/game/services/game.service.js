export class GameService {
  constructor($http, apiConfig, GamePlayers) {
    'ngInject';
    this.$http = $http;
    this.apiConfig = apiConfig;
    this.gamePlayers = GamePlayers;
  }

  newGame() {
    return this.$http
      .post(this.apiConfig.baseUrl + 'game')
      .then(response => response.data)
      .then(gameId => this.getGame(gameId));
  }

  getGames() {
    return this.$http
      .get(this.apiConfig.baseUrl + 'game')
      .then(response => response.data.map(game => this.translateGameResponse(game)));
  }

  getGame(gameId) {
    return this.$http
      .get(this.apiConfig.baseUrl + 'game/' + gameId)
      .then(response => this.translateGameResponse(response.data));
  }

  performMove(gameId, gamePlayer, playerPitIndex) {
    let url = this.apiConfig.baseUrl + 'game/' + gameId + '/move';
    return this.$http.post(url, {
        gamePlayer: gamePlayer,
        playerPitIndex: playerPitIndex,
    }).then(response => this.translateGameResponse(response.data));
  }

  translateGameResponse(game){
    let playerOnePits = game.pits[this.gamePlayers.playerOne];
    let playerTwoPits = game.pits[this.gamePlayers.playerTwo];

    var gameBoard = {
      id: game.id,
      gameState: game.gameState,
      currentPlayer: game.currentPlayer,
      playerScore: {
        [this.gamePlayers.playerOne]: playerOnePits[playerOnePits.length - 1],
        [this.gamePlayers.playerTwo]: playerTwoPits[playerTwoPits.length - 1],
       },
      playerPits: {
        [this.gamePlayers.playerOne]: playerOnePits.slice(0, playerOnePits.length - 1),
        [this.gamePlayers.playerTwo]: playerTwoPits.slice(0, playerOnePits.length - 1),
      },
    };

    return gameBoard;
  }

  isGameOver(game){
    return (game.gameState === 'gameOver');
  }

  isActivePlayer(game, gamePlayer) {
    if (!this.isGameOver(game) && gamePlayer === game.currentPlayer) {
      return true;
    }
  }
}
