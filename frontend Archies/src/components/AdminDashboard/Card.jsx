import Card from 'react-bootstrap/Card';

function CardDes(props) {
  return (
    <>
      {[props.color].map((variant) => (
        <Card bg={variant.toLowerCase()} key={variant} text={variant.toLowerCase() === 'light' ? 'dark' : 'white'}
          style={{ width: '18rem', height:'200px' }}
          className="mb-2"
          onClick={props.onclick}
        >
          <Card.Body>
            <Card.Title style={{fontSize:'30px'}}>{props.title}</Card.Title>
            <Card.Text>
              <h1>{props.counts}</h1>
            </Card.Text>
          </Card.Body>
        </Card>
      ))}
    </>
  );
}

export default CardDes;