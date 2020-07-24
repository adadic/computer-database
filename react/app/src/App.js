import React from 'react';
import 'fontsource-roboto';
import './App.scss';
import Header from "./Component/Header";

import ComputerListDelete from "./Component/ComputerListDelete";
import useAxios from "axios-hooks";
import CircularProgress from "@material-ui/core/CircularProgress";
import ErrorPage from "./Component/ErrorPage";

export const baseURL = 'http://localhost:8080/webapp/api';


function App() {

    return (
        <div className="App">
            <Header/>
        </div>
    );
}

export default App;
