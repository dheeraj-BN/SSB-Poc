import React, { useEffect } from "react";
import Card from "react-bootstrap/Card";
import { Link } from "react-router-dom";

function FCards(props) {
  useEffect(()=>{
    console.log(props.address)
  },[])
  return (
    <Link to={props.address}>
      <Card
        style={{ color: props.color }}
        className="fncards"
        onClick={props.onclick}
      >
        <Card.Img style={{ opacity: 0.7 }} variant="top" src={props.img} />
        <Card.Title className="centered">{props.title}</Card.Title>
      </Card>
    </Link>
  );
}

export default FCards;
