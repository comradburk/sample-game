export class GameService {
  constructor($http) {
    'ngInject';
    this.$http = $http;
  }
  newGame() {
    return this.$http.post('http://localhost:7000/game').then(response => response.data);
  }
}