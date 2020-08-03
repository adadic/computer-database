import React from 'react';
import { Button } from '@material-ui/core';
import { connect } from 'react-redux';
import { getUser } from '../../Store/Selector/UserSelector';
import { getToken } from '../../Store/Selector/ConnexionSelector';
import useAxios from 'axios-hooks';


const baseURL = 'http://localhost:8083/webapp/logout';

function ShowUser(props) {

    const [{ }, onLogout] = useAxios(
        {
            headers: {
                'Authorization': `Bearer ${props.token}`,
            },
            url: `${baseURL}`,
            method: "POST"
        },
        { manual: true }
    );


    function logout() {
        onLogout({ })
            .then((res) => {
                console.log("logout")
            }).catch((error) => {
                console.log(error.status)
            });
        console.log("test")
    }

    return (
        <div>
            <p>{props.user.userName}</p>
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