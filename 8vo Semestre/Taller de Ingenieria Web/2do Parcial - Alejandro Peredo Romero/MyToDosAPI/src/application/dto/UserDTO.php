<?php

namespace Src\application\dto;

class UserDTO
{
    private int $id;
    private string $name;
    private string $lastName;
    private string $phone;
    private string $email;
    private string $dateOfBirth;
    private array $tasks;

    /**
     * @param int $id
     * @param String $name
     * @param String $lastName
     * @param String $phone
     * @param String $email
     * @param String $dateOfBirth
     */
    public function __construct(int $id, string $name, string $lastName, string $phone, string $email, string $dateOfBirth)
    {
        $this->id = $id;
        $this->name = $name;
        $this->lastName = $lastName;
        $this->phone = $phone;
        $this->email = $email;
        $this->dateOfBirth = $dateOfBirth;
        $this->tasks = [];
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
        return $this->name;
    }

    /**
     * @param String $name
     */
    public function setName(string $name): void
    {
        $this->name = $name;
    }

    /**
     * @return String
     */
    public function getLastName(): string
    {
        return $this->lastName;
    }

    /**
     * @param String $lastName
     */
    public function setLastName(string $lastName): void
    {
        $this->lastName = $lastName;
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

    /**
     * @return array
     */
    public function getTasks(): array
    {
        return $this->tasks;
    }

    /**
     * @param array $tasks
     */
    public function setTasks(array $tasks): void
    {
        $this->tasks = $tasks;
    }

    /**
     * @return array
     */

    public function toArray(): array
    {
        return array_filter([
            'id' => $this->id,
            'name' => $this->name,
            'last_name' => $this->lastName,
            'phone' => $this->phone,
            'email' => $this->email,
            'date_of_birth' => $this->dateOfBirth,
            'tasks' => $this->tasks
        ]);
    }
}
