import React from 'react';
import 'fontsource-roboto';
import './App.scss';

import Header from "./Component/Page/Header";
import Register from "./Component/User/Register";
import ComputerDashboard from "./Component/Computer/ComputerDashboard";
import {BrowserRouter, Switch, Route, Redirect} from "react-router-dom";
import Login from "./Component/User/Login";
import CompanyDashboard from "./Component/Company/CompanyDashboard";
import Computer from "./Component/Computer/Computer";
import Company from "./Component/Company/Company";
import About from "./Component/Page/About";
import Logout from "./Component/User/Logout";
import Home from "./Component/Page/Home";
import UserManagement from "./Component/User/UserManagement";
import UserProfile from "./Component/User/UserProfile";

function App() {
    return (
        <BrowserRouter>
                <div className="App">
                    <Header/>
                    <Switch>
                        <Route exact path="/register">
                            <Register />
                        </Route>
                        <Route exact path="/users">
                            <UserManagement />
                        </Route>
                        <Route exact path="/user">
                            <UserProfile />
                        </Route>

                        <Route exact path={"/about"}>
                            <About/>
                        </Route>
                        <Route exact path={"/logout"}>
                            <Logout/>
                        </Route>
                        <Route exact path="/login">
                            <Login/>
                        </Route>
                        <Route exact path={"/computers/:id"}>
                            <Computer/>
                        </Route>
                        <Route exact path={"/companies/:id"}>
                            <Company/>
                        </Route>
                        <Route exact path={"/companies"}>
                            <CompanyDashboard/>
                        </Route>
                        <Route exact path={"/computers"}>
                            <ComputerDashboard />
                        </Route>
                        <Route exact path={"/AddComputer"}>
                            <ComputerDashboard addMode={true}/>
                        </Route>
                        <Route exact path={"/home"}>
                            <Home />
                        </Route>
                        <Route exact path={"/"}>
                            <Redirect to="/home"/>
                        </Route>

                    </Switch>
                </div>
        </BrowserRouter>
    );
}

export default App;