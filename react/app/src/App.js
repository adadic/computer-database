import React, {useEffect, useState} from 'react';
import 'fontsource-roboto';
import './App.scss';
import Header from "./Component/Header/Header";
import TableList from "./Component/TableList/TableList";
import useAxios from "axios-hooks";
import CircularProgress from "@material-ui/core/CircularProgress";

export const baseURL = 'http://localhost:8083/webapp/api';

function App() {


    const [{ data, loading, error }] = useAxios(baseURL + "/computers");
    const [computers, setComputers] = useState(data);
    useEffect(() => setComputers(data),[data]);

    return (
        <div className="App">
            <Header/>
            {error && "Error!!!!"}
            {loading
                ?
                <CircularProgress/>
                :
                computers && <TableList computers={computers}/>
            }
        </div>
    );
}

export default App;
