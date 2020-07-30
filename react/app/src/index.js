import React, {StrictMode} from 'react';
import ReactDOM from 'react-dom';
import './Index.css';
import App from './App';
import * as serviceWorker from './ServiceWorker';
import {Provider} from "react-redux";
import RootReducer from "./Store/Reducers/RootReducer";
import { createStore } from "redux";

const store = createStore(RootReducer, window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());

ReactDOM.render(
    <React.StrictMode>
      <Provider store={store}>
        <App/>
      </Provider>
    </React.StrictMode>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
