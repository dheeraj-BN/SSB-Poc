import React from "react";
import Card from 'react-bootstrap/Card';


function FCards(props) {
  
  return (
    <Card style={{color : props.color}} className="fncards" onClick={props.onclick}>
      <Card.Img style={{opacity: 0.7}} variant="top" src={props.img} />
      <Card.Title className="centered">{props.title}</Card.Title>
    </Card>
  );
}

export default FCards;
