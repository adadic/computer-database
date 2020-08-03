import React, { useState, useEffect } from 'react';
import { Button } from '@material-ui/core';
import { connect } from 'react-redux';
import { getUser } from '../../Store/Selector/UserSelector'
import { getToken } from '../../Store/Selector/ConnexionSelector'


export const baseURL = 'http://localhost:8083/webapp/api/users';

function ShowUser(props) {

    return(
        <div>
            <p>{props.user.userName}</p>
            <p>{props.user.roleName}</p>
            <Button>Logout</Button>
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