import React, { useState } from 'react';
import { Code, Tag, CircularProgress } from '@chakra-ui/react';
import {
  Table,
  Thead,
  Tbody,
  Tr,
  Th,
  Td,
  TableCaption,
} from '@chakra-ui/react';
import { get, getURL } from '../api';
import { PageError } from './PageError';

const Ingredients = ({ ingredients }) =>
  ingredients.map(name => <Tag margin="2px">{name}</Tag>);

const PizzaTable = ({ type, pizzas }) => (
  <Table>
    <TableCaption>
      Pizza retrieved using{' '}
      <Code>
        {getURL()}/pizzas?type={type}
      </Code>
    </TableCaption>
    <Thead>
      <Tr>
        <Th>Nom</Th>
        <Th isNumeric minWidth="10em">
          Prix
        </Th>
        <Th>Ingrédients</Th>
      </Tr>
    </Thead>
    <Tbody>
      {pizzas.map(({ name, prix, ingredients }) => (
        <Tr key={`${type}-${name}`}>
          <Td>{name}</Td>
          <Td isNumeric>{prix} €</Td>
          <Td>
            <Ingredients ingredients={ingredients} />
          </Td>
        </Tr>
      ))}
    </Tbody>
  </Table>
);

function PizzaTableComponent({ type }) {
  const [pizzaAPI, setPizzaAPI] = useState({ list: [] });
  const [pageError, setPageError] = useState();

  if (pizzaAPI.type !== type) {
    get(`?type=${type}`)
      .then(response => {
        setPageError();
        setPizzaAPI({ type, list: response });
      })
      .catch(error => {
        setPageError(error);
        setPizzaAPI({ type });
      });
  }
  return (
    <>
      {(!pizzaAPI.type || pizzaAPI.type !== type) && !pageError ? (
        <CircularProgress isIndeterminate color="teal" />
      ) : pageError ? (
        <PageError error={pageError} />
      ) : (
        <PizzaTable type={type} pizzas={pizzaAPI.list}></PizzaTable>
      )}
    </>
  );
}

export default PizzaTableComponent;
