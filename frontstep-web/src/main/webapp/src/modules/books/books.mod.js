(
  function (ng) {
    var mod = ng.module("bookModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        // En basePath se encuentran los templates y controladores de módulo
        var basePath = 'src/modules/books/';
        // Mostrar la lista de libros será el estado por defecto del módulo
        $urlRouterProvider.otherwise("/booksList");
        // Definición del estado 'booksList' donde se listan los libros
        $stateProvider.state('booksList', {
          // Url que aparecerá en el browser
          url: '/books/list',
          // Se define una variable books (del estado) que toma por valor 
          // la colección de libros que obtiene utilizando $http.get 
          resolve: {
            books: ['$http', function ($http) {
                // $http retorna una promesa que aquí no se está manejando si viene con error.
                return $http.get('data/books.json'); 
              }]
          },
          // Template que se utilizará para ejecutar el estado
          templateUrl: basePath + 'books.list.html',
          // El controlador guarda en el scope en la variable booksRecords los datos que trajo el resolve
          // booksRecords será visible en el template
          controller: ['$scope', 'books', function ($scope, books) {
              $scope.booksRecords = books.data;
            }]
        });
      }
    ]);
        }
)(window.angular);
