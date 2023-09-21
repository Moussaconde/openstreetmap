import { Box, HStack, Link } from '@chakra-ui/react';
export const Footer = () => (
  <Box bg="grey" padding="1em">
    <HStack>
      <Link
        fontSize="lg"
        color="white"
        href="https://joxit.dev/IG-Master2"
        target="_blank"
      >
        Pizzeria UI
      </Link>
      <Link
        fontSize="md"
        color="gray.200"
        href="https://github.com/Joxit/IG-Master2"
        target="_blank"
      >
        Projet sur GitHub
      </Link>
      <Link
        fontSize="md"
        color="gray.200"
        href="https://joxit.dev/IG-Master2/beamer/spring-hibernate.pdf"
        target="_blank"
      >
        Cours Spring Hibernate
      </Link>
    </HStack>
  </Box>
);
