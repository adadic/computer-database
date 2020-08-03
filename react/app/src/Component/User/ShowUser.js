import React from 'react';
import { Button } from '@material-ui/core';
import { connect } from 'react-redux';
import { getUser } from '../../Store/Selector/UserSelector';
import { getToken } from '../../Store/Selector/ConnexionSelector';
import useAxios from 'axios-hooks';
import { useHistory } from "react-router-dom";


const baseURL = 'http://localhost:8083/webapp/logout';

function ShowUser(props) {

    const history = useHistory();

    function logout() {
        history.push("/logout")
    }

    return (
        <div>
            <p>{props.user.userName}</p>
            <p>{props.user.email}</p>
            <p>{props.user.roleName}</p>
            <Button variant="outlined" onClick={logout} color="primary">Logout</Button>
        </div>
    );
}

const mapStateToProps = (state) => {
    return {
        user: getUser(state),
        token: getToken(state),
    }
}

export default connect(mapStateToProps, null)(ShowUser);