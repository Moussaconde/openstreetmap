import { Box, Grid, Text } from '@chakra-ui/react';
export const Header = ({ children }) => (
  <Box bg="teal" padding="1em">
    <Grid templateColumns="repeat(2, 1fr)" gap="4">
      <Text fontSize="3xl" color="white">
        Pizzeria
      </Text>
      {children}
    </Grid>
  </Box>
);
