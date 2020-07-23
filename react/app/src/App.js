import React, {useEffect, useState} from 'react';
import 'fontsource-roboto';
import './App.scss';
import Header from "./Component/Header/Header";
import TableList, {baseURL} from "./Component/TableList/TableList";
import {MOCK} from "./Mock";
import useAxios from "axios-hooks";

function App() {


    const {data} = useAxios(baseURL + "/computers");
    const [computers, setComputers] = useState(data);
    useEffect(() => setComputers(data),data);

    return (
        <div className="App">
            <Header/>

            <div>
                Console :
                <br/>
                {computers === undefined ? "True" : "False"}
                <br/>
                {computers}
            </div>
        </div>
    );
}

export default App;
