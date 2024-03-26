import { UserRoles } from './user-roles';

export type User = {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  roles: UserRoles;
  clubId: number;
};
