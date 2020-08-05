import React from "react";
import imageOne from "./resources/imageOne.png";

function ErrorPage(props) {

    return(
        <div>
            <img
                style={{
                    position:'absolute',
                    height:'100%',
                    width:'100%',
                    left:'0',
                    top: '0',
                    zIndex:'-1',
                    opacity:'0.2'
                }}
                src='https://wallpaperaccess.com/full/1180986.jpg'
            />
            {props.errorMessage
            ?
                <div>{props.errorMessage}</div>
            :
                <div>ERROR!!!!!!</div>
            }
        </div>
    );
}
export default ErrorPage;