<?php

namespace Src\domain\entities\user;

use Src\domain\entities\user\valueObjects\UserLastNameValue;
use Src\domain\entities\user\valueObjects\UserNameValue;
use Src\domain\entities\user\valueObjects\UserPasswordValue;

class User
{
    private int $id;
    private UserNameValue $name;
    private UserLastNameValue $lastName;
    private String $phone;
    private String $email;
    private UserPasswordValue $password;
    private String $dateOfBirth;

    /**
     * @param int $id
     * @param String $name
     * @param String $lastName
     * @param String $phone
     * @param String $email
     * @param String $password
     * @param String $dateOfBirth
     */
    public function __construct(int $id, string $name, string $lastName, string $phone, string $email, string $password, string $dateOfBirth)
    {
        $this->id = $id;
        $this->name = new UserNameValue($name);
        $this->lastName = new UserLastNameValue($lastName);
        $this->phone = $phone;
        $this->email = $email;
        $this->password = new UserPasswordValue($password);
        $this->dateOfBirth = $dateOfBirth;
    }

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId(int $id): void
    {
        $this->id = $id;
    }

    /**
     * @return String
     */
    public function getName(): string
    {
        return $this->name->getValue();
    }

    /**
     * @param String $name
     */
    public function setName(string $name): void
    {
        $this->name = new UserNameValue($name);
    }

    /**
     * @return String
     */
    public function getLastName(): string
    {
        return $this->lastName->getValue();
    }

    /**
     * @param String $lastName
     */
    public function setLastName(string $lastName): void
    {
        $this->lastName = new UserLastNameValue($lastName);
    }

    /**
     * @return String
     */
    public function getPhone(): string
    {
        return $this->phone;
    }

    /**
     * @param String $phone
     */
    public function setPhone(string $phone): void
    {
        $this->phone = $phone;
    }

    /**
     * @return String
     */
    public function getEmail(): string
    {
        return $this->email;
    }

    /**
     * @param String $email
     */
    public function setEmail(string $email): void
    {
        $this->email = $email;
    }

    /**
     * @return String
     */
    public function getPassword(): string
    {
        return $this->password->getValue();
    }

    /**
     * @param String $password
     */
    public function setPassword(string $password): void
    {
        $this->password = new UserPasswordValue($password);
    }

    /**
     * @return String
     */
    public function getDateOfBirth(): string
    {
        return $this->dateOfBirth;
    }

    /**
     * @param String $dateOfBirth
     */
    public function setDateOfBirth(string $dateOfBirth): void
    {
        $this->dateOfBirth = $dateOfBirth;
    }

    public function toArray(): array
    {
        return [
            'id' => $this->id,
            'name' => $this->name->getValue(),
            'last_name' => $this->lastName->getValue(),
            'phone' => $this->phone,
            'email' => $this->email,
            'password' => $this->password->getValue(),
            'date_of_birth' => $this->dateOfBirth
        ];
    }

}
