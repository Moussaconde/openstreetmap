import {
  Alert,
  AlertIcon,
  AlertTitle,
  AlertDescription,
} from '@chakra-ui/react';
import { Code } from '@chakra-ui/react';

export const PageError = ({ error }) => {
  let json;
  try {
    json = JSON.parse(error);
  } catch (e) {}
  const description = json?.message || json?.error || error.message;

  return (
    <Alert
      status="error"
      variant="subtle"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
      textAlign="center"
      height="200px"
    >
      <AlertIcon boxSize="40px" />
      <AlertTitle mt={4} mb={1} fontSize="lg">
        {description}
      </AlertTitle>
      <AlertDescription>
        <Code>{error}</Code>
      </AlertDescription>
    </Alert>
  );
};
