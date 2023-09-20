import React, { useState } from 'react';
import { ChakraProvider, Box, VStack, Grid, theme } from '@chakra-ui/react';
import { Button } from '@chakra-ui/react';
import { ColorModeSwitcher } from './components/ColorModeSwitcher';
import PizzaTable from './components/PizzaTable';
import { Card, CardHeader, CardBody } from '@chakra-ui/react';
import { Header } from './components/Header';
import { Footer } from './components/Footer';

function App() {
  const [type, setType] = useState('lazy');
  const types = [
    'lazy',
    'eager',
    'custom',
    'spring-data-jpa',
    'errorHandled',
    'errorNotHandled',
  ];
  return (
    <ChakraProvider theme={theme}>
      <Header>
        <ColorModeSwitcher justifySelf="flex-end" />
      </Header>
      <Box textAlign="center" fontSize="xl">
        <VStack>
          <Card>
            <CardHeader>
              {types.map(curType => (
                <Button
                  colorScheme="teal"
                  variant={curType === type ? 'outline' : 'solid'}
                  margin="2"
                  onClick={() => setType(curType)}
                >
                  {curType}
                </Button>
              ))}
            </CardHeader>
            <CardBody>
              <PizzaTable type={type} />
            </CardBody>
          </Card>
        </VStack>
      </Box>
      <Footer></Footer>
    </ChakraProvider>
  );
}

export default App;
