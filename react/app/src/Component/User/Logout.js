import React, {useEffect} from 'react';
import {Redirect} from "react-router-dom";
import {connect} from "react-redux";
import {isConnected, setToken} from "../../Store/Action/ConnexionAction";

function Logout(props){

    useEffect(() => {
        props.setToken("");
        props.setConnected(false);
        localStorage.clear();
    });

    return (
        <Redirect to="/home"/>
    );
}
const mapDispatchToProps = dispatch => {

    return {
        setToken: token => dispatch(setToken(token)),
        setConnected: conn => dispatch(isConnected(conn))
    }
}

export default connect(null, mapDispatchToProps)(Logout);