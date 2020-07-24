import React from 'react';
import 'fontsource-roboto';
import './App.scss';
import Header from "./Component/Header";
import Dashboard from "./Component/Dashboard";

function App() {

    return (
        <div className="App">
            <Header/>
            <Dashboard/>
        </div>
    );
}

export default App;
