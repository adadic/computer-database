import React from 'react';
import 'fontsource-roboto';
import './App.scss';

import Header from "./Component/Page/Header/Header";
import Register from "./Component/User/Register";
import ComputerDashboard from "./Component/Computer/ComputerDashboard";
import { BrowserRouter, Switch, Route, Redirect } from "react-router-dom";
import Login from "./Component/User/Login";
import CompanyDashboard from "./Component/Company/CompanyDashboard";
import Computer from "./Component/Computer/Computer";
import Company from "./Component/Company/Company";
import UsersDashboard from "./Component/User/UserDashboard"
import About from "./Component/Page/About";
import Home from "./Component/Page/Home";
import { isConnected, setToken } from "./Store/Action/ConnexionAction";
import { setUser } from './Store/Action/UserAction';
import { connect } from "react-redux";
import Logout from "./Component/User/Logout";

function App(props) {

    return (
        <BrowserRouter>
            <div className="App">
                <Header />
                {props.token !== "" && props.isConnected
                    ?
                    <Switch>
                        <Route exact path="/register">
                            <Register />
                        </Route>
                        <Route exact path={"/about"}>
                            <About />
                        </Route>
                        <Route exact path={"/logout"}>
                            <Logout />
                        </Route>
                        <Route exact path="/login">
                            <Login />
                        </Route>
                        <Route exact path={"/computers/:id"}>
                            <Computer />
                        </Route>
                        <Route exact path={"/companies/:id"}>
                            <Company />
                        </Route>
                        <Route exact path={"/companies"}>
                            <CompanyDashboard />
                        </Route>
                        <Route exact path={"/computers"}>
                            <ComputerDashboard />
                        </Route>
                        <Route exact path={"/users"}>
                            <UsersDashboard />
                        </Route>
                        <Route exact path={"/AddComputer"}>
                            <ComputerDashboard addMode={true} />
                        </Route>
                        <Route exact path={"/home"}>
                            <Home />
                        </Route>
                        <Route exact path={"/"}>
                            <Redirect to="/home" />
                        </Route>

                    </Switch>
                    :
                    <Switch>
                        <Route exact path={"/about"}>
                            <About />
                        </Route>
                        <Route exact path={"/home"}>
                            <Home />
                        </Route>
                        <Route exact path={"/register"}>
                            <Register />
                        </Route>
                        <Route path={"/"}>
                            <Redirect to="/home" />
                        </Route>
                    </Switch>
                }
            </div>
        </BrowserRouter>
    );
}

const mapStateToProps = (state) => {
    return {
        token: localStorage.getItem('token'),
        isConnected: localStorage.getItem('isConnected'),
    }
}

const mapDispatchToProps = dispatch => {
    return {

        setToken: dispatch(setToken(localStorage.getItem('token'))),
        setConnected: dispatch(isConnected(localStorage.getItem('isConnected'))),
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(App);