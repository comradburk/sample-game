export class GameListService {
  constructor($http) {
    'ngInject';
    this.$http = $http;
  }
  getGames() {
    return this.$http.get('http://localhost:7000/game').then(response => response.data);
  }
}