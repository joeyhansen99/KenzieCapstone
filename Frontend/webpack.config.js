const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyPlugin = require("copy-webpack-plugin");
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
  optimization: {
    usedExports: true
  },
  entry: {
    indexPage: path.resolve(__dirname, 'src', 'pages', 'indexPage.js'),
    collectionPage: path.resolve(__dirname, 'src', 'pages', 'collectionPage.js'),
    addCardPage: path.resolve(__dirname, 'src', 'pages', 'addCardPage.js')
  },
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: '[name].js',
  },
  devServer: {
    https: false,
    port: 8080,
    open: true,
    openPage: 'http://localhost:8080/index.html',
    // disableHostChecks, otherwise we get an error about headers and the page won't render
    disableHostCheck: true,
    contentBase: 'packaging_additional_published_artifacts',
    // overlay shows a full-screen overlay in the browser when there are compiler errors or warnings
    overlay: true,
    proxy: [
        {
            context: [
                '/cards'
            ],
            target: 'http://localhost:5001'
        }
    ]
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: './src/index.html',
      filename: 'index.html',
      inject: false
    }),
    new HtmlWebpackPlugin({
      template: './src/collection.html',
      filename: 'collection.html',
      inject: false
    }),
    new HtmlWebpackPlugin({
      template: './src/addCard.html',
      filename: 'addCard.html',
      inject: false
    }),
    new CopyPlugin({
      patterns: [
        {
          from: path.resolve('src/css'),
          to: path.resolve("dist/css")
        },
        {
          from: path.resolve('src/images'),
          to: path.resolve("dist/images")
        }
      ]
    }),
    new CleanWebpackPlugin()
  ]
}
