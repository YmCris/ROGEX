interface CrudState<T> {
  loading: boolean;
  success: boolean;
  error: boolean;
  message: string;
  data: T[];
}
